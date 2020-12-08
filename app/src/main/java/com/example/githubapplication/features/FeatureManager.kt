package com.example.githubapplication.features

import android.widget.Toast
import com.example.githubapplication.base.BaseApplication
import com.example.githubapplication.base.appComponent
import com.google.android.play.core.splitinstall.*
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.example.githubapplication.features.FeatureName.*
import java.util.*

infix fun FeatureManager.getFeature(featureName: FeatureName) = when (featureName) {
    GitHubRepoSearchFeature -> injectFeature<GitHubRepoSearchFeature, GitHubRepoSearchFeature.Dependencies>(appComponent.gitHubRepoSearchFeature, featureName)
}

private inline fun <reified T : Feature<D>, D> FeatureManager.injectFeature(
    dependencies: D, featureName: FeatureName
): T? {
    return if (isFeatureInstalled(featureName)) {
        val serviceIterator = ServiceLoader.load(
            T::class.java,
            T::class.java.classLoader
        ).iterator()

        if (serviceIterator.hasNext()) {
            (serviceIterator.next()).apply { inject(dependencies) }
        } else {
            null
        }
    } else {
        null
    }
}

fun FeatureManager.isFeatureInstalled(featureName: FeatureName): Boolean = isFeatureDownloaded(featureName)

interface FeatureManager {
    fun downloadFeature(featureName: FeatureName)
    fun isFeatureDownloaded(featureName: FeatureName): Boolean
    fun registerInstallListener(listener: (FeatureName) -> Unit)
    fun unregisterInstallListener(listener: (FeatureName) -> Unit)
}

internal class FeatureManagerImpl(private val applicationClass: BaseApplication) : FeatureManager {

    private val splitInstallManager: SplitInstallManager = SplitInstallManagerFactory.create(applicationClass)
    private val installListeners = mutableListOf<(FeatureName) -> Unit>()

    private lateinit var toast: Toast

    override fun downloadFeature(featureName: FeatureName) {
        val request = SplitInstallRequest.newBuilder()
            .addModule(featureName.moduleName)
            .build()

        val installStateUpdateListener = object : SplitInstallStateUpdatedListener {
            override fun onStateUpdate(state: SplitInstallSessionState) {
                state.moduleNames().forEach { moduleName ->
                    when (state.status()) {
                        SplitInstallSessionStatus.CANCELED -> {
                            splitInstallManager.unregisterListener(this)
                            showToast(applicationClass, "CANCELED $moduleName = ${state.status()}")
                        }
                        SplitInstallSessionStatus.CANCELING -> {
                            showToast(applicationClass, "CANCELING $moduleName = ${state.status()}")
                        }
                        SplitInstallSessionStatus.DOWNLOADED -> {
                            showToast(applicationClass, "DOWNLOADED $moduleName = ${state.status()}")
                        }
                        SplitInstallSessionStatus.DOWNLOADING -> {
                            showToast(applicationClass, "DOWNLOADING $moduleName = ${state.status()}")
                        }
                        SplitInstallSessionStatus.FAILED -> {
                            splitInstallManager.unregisterListener(this)
                            showToast(applicationClass, "FAILED $moduleName = ${state.status()}")
                        }
                        SplitInstallSessionStatus.INSTALLED -> {
                            splitInstallManager.unregisterListener(this)
                            installListeners.forEach { listener -> listener(featureName) }
                            showToast(applicationClass, "INSTALLED $moduleName = ${state.status()}")
                        }
                        SplitInstallSessionStatus.INSTALLING -> {
                            showToast(applicationClass, "INSTALLING $moduleName = ${state.status()}")
                        }
                        SplitInstallSessionStatus.PENDING -> {
                            showToast(applicationClass, "PENDING $moduleName = ${state.status()}")
                        }
                        SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                            showToast(applicationClass, "REQUIRES_USER_CONFIRMATION $moduleName = ${state.status()}")
                        }
                        SplitInstallSessionStatus.UNKNOWN -> {
                            splitInstallManager.unregisterListener(this)
                            showToast(applicationClass, "UNKNOWN $moduleName = ${state.status()}")
                        }
                    }
                }
            }
        }

        splitInstallManager.registerListener(installStateUpdateListener)
        splitInstallManager.startInstall(request)
    }

    override fun isFeatureDownloaded(featureName: FeatureName): Boolean {
        return splitInstallManager.installedModules.contains(featureName.moduleName)
    }

    override fun registerInstallListener(listener: (FeatureName) -> Unit) {
        installListeners.add(listener)
    }

    override fun unregisterInstallListener(listener: (FeatureName) -> Unit) {
        installListeners.remove(listener)
    }

    private fun showToast(context: BaseApplication, s: String) {
        toast.cancel()
        toast = Toast.makeText(context, s, Toast.LENGTH_SHORT)
        toast.show()
    }
}