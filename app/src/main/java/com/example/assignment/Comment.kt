package com.example.assignment

class Comment(
    var commentId: Int,
    var memId: Int,
    var forumId: Int,
    var parentId: Int,
    var comment: String,
) {
    constructor(memId: Int, forumId: Int, parentId: Int, comment: String):
            this(0, memId, forumId, parentId, comment)
}