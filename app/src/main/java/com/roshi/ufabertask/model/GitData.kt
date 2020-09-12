package com.roshi.ufabertask.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class GitData(
    @SerializedName("id")
    @Expose
    val id: Long = 0,

    @SerializedName("node_id")
    @Expose
    val nodeId: String? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("full_name")
    @Expose
    val fullName: String? = null,

    @SerializedName("private")
    @Expose
    val _private: Boolean = false,

    @SerializedName("owner")
    @Expose
    val owner: Owner? = null,

    @SerializedName("html_url")
    @Expose
    val htmlUrl: String? = null,

    @SerializedName("description")
    @Expose
    val description: String? = null,

    @SerializedName("fork")
    @Expose
    val fork: Boolean = false,

    @SerializedName("url")
    @Expose
    val url: String? = null,

    @SerializedName("forks_url")
    @Expose
    val forksUrl: String? = null,

    @SerializedName("keys_url")
    @Expose
    val keysUrl: String? = null,

    @SerializedName("collaborators_url")
    @Expose
    val collaboratorsUrl: String? = null,

    @SerializedName("teams_url")
    @Expose
    val teamsUrl: String? = null,

    @SerializedName("hooks_url")
    @Expose
    val hooksUrl: String? = null,

    @SerializedName("issue_events_url")
    @Expose
    val issueEventsUrl: String? = null,

    @SerializedName("events_url")
    @Expose
    val eventsUrl: String? = null,

    @SerializedName("assignees_url")
    @Expose
    val assigneesUrl: String? = null,

    @SerializedName("branches_url")
    @Expose
    val branchesUrl: String? = null,

    @SerializedName("tags_url")
    @Expose
    val tagsUrl: String? = null,

    @SerializedName("blobs_url")
    @Expose
    val blobsUrl: String? = null,

    @SerializedName("git_tags_url")
    @Expose
    val gitTagsUrl: String? = null,

    @SerializedName("git_refs_url")
    @Expose
    val gitRefsUrl: String? = null,

    @SerializedName("trees_url")
    @Expose
    val treesUrl: String? = null,

    @SerializedName("statuses_url")
    @Expose
    val statusesUrl: String? = null,

    @SerializedName("languages_url")
    @Expose
    val languagesUrl: String? = null,

    @SerializedName("stargazers_url")
    @Expose
    val stargazersUrl: String? = null,

    @SerializedName("contributors_url")
    @Expose
    val contributorsUrl: String? = null,

    @SerializedName("subscribers_url")
    @Expose
    val subscribersUrl: String? = null,

    @SerializedName("subscription_url")
    @Expose
    val subscriptionUrl: String? = null,

    @SerializedName("commits_url")
    @Expose
    val commitsUrl: String? = null,

    @SerializedName("git_commits_url")
    @Expose
    val gitCommitsUrl: String? = null,

    @SerializedName("comments_url")
    @Expose
    val commentsUrl: String? = null,

    @SerializedName("issue_comment_url")
    @Expose
    val issueCommentUrl: String? = null,

    @SerializedName("contents_url")
    @Expose
    val contentsUrl: String? = null,

    @SerializedName("compare_url")
    @Expose
    val compareUrl: String? = null,

    @SerializedName("merges_url")
    @Expose
    val mergesUrl: String? = null,

    @SerializedName("archive_url")
    @Expose
    val archiveUrl: String? = null,

    @SerializedName("downloads_url")
    @Expose
    val downloadsUrl: String? = null,

    @SerializedName("issues_url")
    @Expose
    val issuesUrl: String? = null,

    @SerializedName("pulls_url")
    @Expose
    val pullsUrl: String? = null,

    @SerializedName("milestones_url")
    @Expose
    val milestonesUrl: String? = null,

    @SerializedName("notifications_url")
    @Expose
    val notificationsUrl: String? = null,

    @SerializedName("labels_url")
    @Expose
    val labelsUrl: String? = null,

    @SerializedName("releases_url")
    @Expose
    val releasesUrl: String? = null,

    @SerializedName("deployments_url")
    @Expose
    val deploymentsUrl: String? = null
) : Serializable {
    companion object {
        val CALLBACK: DiffUtil.ItemCallback<GitData> = object : DiffUtil.ItemCallback<GitData>() {
            override fun areItemsTheSame(oldItem: GitData, newItem: GitData): Boolean {
                return oldItem.id == newItem.id

            }

            override fun areContentsTheSame(oldItem: GitData, newItem: GitData): Boolean {
                return true
            }


        }
    }
}