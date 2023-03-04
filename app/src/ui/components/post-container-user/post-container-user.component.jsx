import {
  Avatar,
  Box,
  Typography,
  Button,
  Modal,
  TextField,
} from '@material-ui/core';
import { likePost, commentPost } from '../../../api';
import { useState } from 'react';
import { toast } from 'react-toastify';

import './post-container-user.style.css';

export function PostContainerUser({ post }) {
  const [comment, setComment] = useState({
    comment: '',
  });

  const [openModal, setOpenModal] = useState(false);

  const handleCommentChange = (event) => {
    setComment({
      ...comment,
      [event.target.name]: event.target.value,
    });
  };

  const handleCommentSubmit = async (event) => {
    event.preventDefault();
    try {
      await commentPost(post.id, comment);
      toast(`Successful commented!`);
      setOpenModal(false);
      setOpenModal({
        comment: '',
      });
    } catch (error) {
      toast(`${error.response.statusText}!`);
    }
  };

  return (
    <Box
      display="flex"
      alignItems="center"
      justifyContent="center"
      padding={2}
      boxShadow={1}
    >
      <Box marginRight={2} className="post-container-left">
        <Avatar src={post.usersId.profileImage} alt={post.usersId.name} />
        <Typography variant="subtitle2">{post.usersId.name}</Typography>

        <Box>
          <Button
            variant="contained"
            color="primary"
            size="small"
            onClick={() => likePost(post.id)}
          >
            Like
          </Button>

          <Button
            variant="contained"
            color="primary"
            size="small"
            onClick={() => setOpenModal(true)}
          >
            Comment
          </Button>
          <Modal
            open={openModal}
            onClose={() => setOpenModal(false)}
            aria-labelledby="Register Modal"
            aria-describedby="Modal for user registration"
            className="registerModal"
          >
            <form className="form" onSubmit={handleCommentSubmit}>
              <Typography variant="h4" align="center" gutterBottom>
                Make a comment
              </Typography>
              <TextField
                label="Comment"
                variant="outlined"
                name="comment"
                value={comment.comment}
                onChange={handleCommentChange}
                className="textField"
              />
              <Button
                variant="contained"
                color="primary"
                type="submit"
                className="button"
              >
                Comment
              </Button>
            </form>
          </Modal>
        </Box>

        <Box>
          Likes: {post.likes.length} ⭐ Comments: {post.comments.length}
        </Box>
      </Box>

      <Box flexGrow={1} className="post-container-middle">
        <Typography variant="body1">{post.description}</Typography>
        {post.postImage && (
          <img
            src={post.postImage}
            alt={post.description}
            className="avatar-image"
          />
        )}
      </Box>

      <Box marginLeft={2} className="post-container-right">
        <Typography variant="caption">{post.privacy}</Typography>
        <Typography variant="caption">{post.postDate}</Typography>
      </Box>
    </Box>
  );
}
