package com.example.githubapplication.features

import android.widget.Toast
import com.example.githubapplication.base.BaseApplication
import com.example.githubapplication.base.appComponent
import com.google.android.play.core.splitinstall.*
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import java.util.*

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

infix fun FeatureManager.getFeature(featureName: FeatureName) = when (featureName) {
    FeatureName.GitHubRepoSearchFeature -> injectFeature<GitHubRepoSearchFeature, GitHubRepoSearchFeature.Dependencies>(appComponent.gitHubRepoSearchFeature, featureName)
}

fun FeatureManager.installFeature(featureName: FeatureName) = downloadFeature(featureName)

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
                            Toast.makeText(applicationClass, "CANCELED $moduleName = ${state.status()}", Toast.LENGTH_SHORT).show()
                        }
                        SplitInstallSessionStatus.CANCELING -> {
                            Toast.makeText(applicationClass, "CANCELING $moduleName = ${state.status()}", Toast.LENGTH_SHORT).show()
                        }
                        SplitInstallSessionStatus.DOWNLOADED -> {
                            Toast.makeText(applicationClass, "DOWNLOADED $moduleName = ${state.status()}", Toast.LENGTH_SHORT).show()
                        }
                        SplitInstallSessionStatus.DOWNLOADING -> {
                            Toast.makeText(applicationClass, "DOWNLOADING $moduleName = ${state.status()}", Toast.LENGTH_SHORT).show()
                        }
                        SplitInstallSessionStatus.FAILED -> {
                            splitInstallManager.unregisterListener(this)
                            Toast.makeText(applicationClass, "FAILED $moduleName = ${state.status()}", Toast.LENGTH_SHORT).show()
                        }
                        SplitInstallSessionStatus.INSTALLED -> {
                            splitInstallManager.unregisterListener(this)
                            installListeners.forEach { listener -> listener(featureName) }
                            Toast.makeText(applicationClass, "INSTALLED $moduleName = ${state.status()}", Toast.LENGTH_SHORT).show()
                        }
                        SplitInstallSessionStatus.INSTALLING -> {
                            Toast.makeText(applicationClass, "INSTALLING $moduleName = ${state.status()}", Toast.LENGTH_SHORT).show()
                        }
                        SplitInstallSessionStatus.PENDING -> {
                            Toast.makeText(applicationClass, "PENDING $moduleName = ${state.status()}", Toast.LENGTH_SHORT).show()
                        }
                        SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                            Toast.makeText(applicationClass, "REQUIRES_USER_CONFIRMATION $moduleName = ${state.status()}", Toast.LENGTH_SHORT).show()
                        }
                        SplitInstallSessionStatus.UNKNOWN -> {
                            splitInstallManager.unregisterListener(this)
                            Toast.makeText(applicationClass, "UNKNOWN $moduleName = ${state.status()}", Toast.LENGTH_SHORT).show()
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
}