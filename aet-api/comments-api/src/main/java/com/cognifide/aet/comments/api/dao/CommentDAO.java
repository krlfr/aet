/*
 * Cognifide AET :: Comments API
 *
 * Copyright (C) 2013 Cognifide Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cognifide.aet.comments.api.dao;

import com.cognifide.aet.comments.api.Comment;
import com.cognifide.aet.communication.api.node.NodeMetadata;
import com.cognifide.aet.communication.api.OperationStatus;

import java.util.Collection;

public interface CommentDAO {

	/**
	 * Gets collection of comments that match provided NodeMatadata.
	 *
	 * @param metadata
	 * @return Collection of comments
	 *
	 * @see NodeMetadata
	 */
	Collection<Comment> getAllComments(NodeMetadata metadata);

	/**
	 * Gets comment that match provided NodeMatadata and specified Comment Level.
	 *
	 * @param metadata
	 * @param level
	 * @return Comment
	 *
	 * @see NodeMetadata
	 */
	Comment getComment(NodeMetadata metadata, Comment.Level level);

	/**
	 * If corresponding report exists then creates comment document in database or updates comment's content
	 * if comment already exists.
	 */
	OperationStatus createOrUpdateComment(Comment comment);

	/**
	 * Deletes specified comment document from database.
	 */
	OperationStatus deleteComment(Comment comment);

	/**
	 * Deletes from database comments that match provided metadata.
	 *
	 * @see NodeMetadata
	 */
	OperationStatus deleteComments(NodeMetadata metadata);

}
