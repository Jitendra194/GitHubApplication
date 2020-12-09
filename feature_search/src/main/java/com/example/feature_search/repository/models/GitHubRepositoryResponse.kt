package com.example.feature_search.repository.models

data class GitHubRepositoryResponse(
    val total_count: Int,
    val items: List<Item>
)