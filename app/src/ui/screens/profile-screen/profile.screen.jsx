import React, { useEffect, useState } from 'react';
import {
  Container,
  Box,
  Typography,
  Button,
  Modal,
  TextField,
  Select,
  MenuItem,
} from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import {
  meInformations,
  acceptFriendRequest,
  editProfile,
  createPost,
  listMyAndFriendsPosts,
  searchFriends
} from '../../../api';
import { toast } from 'react-toastify';
import { Navbar, PostContainer } from '../../components';
import { useNavigate } from 'react-router-dom';

import "./profile.style.css"

const useStyles = makeStyles((theme) => ({
  container: {
    display: 'flex',
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'flex-start',
    paddingTop: '100px',
  },
  leftContainer: {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    marginBottom: theme.spacing(2),
  },
  profileImage: {
    width: '200px',
    height: '200px',
    borderRadius: '50%',
    objectFit: 'cover',
  },
  rightContainer: {
    overflowY: 'auto',
    height: '295px',
  },
  friendRequestItem: {
    display: 'flex',
    alignItems: 'center',
    padding: theme.spacing(1),
    marginBottom: theme.spacing(1),
    backgroundColor: theme.palette.grey[200],
    borderRadius: theme.shape.borderRadius,
  },
  friendRequestSenderImage: {
    width: '50px',
    height: '50px',
    borderRadius: '50%',
    objectFit: 'cover',
    marginRight: theme.spacing(2),
  },
  buttonContainer: {
    display: 'flex',
    flexDirection: 'row',
    justifyContent: 'space-between',
    width: '100%',
    marginTop: theme.spacing(2),
  },
}));

export function ProfileScreen() {
  const classes = useStyles();
  const [meInfo, setMeInfo] = useState({});
  const [friendRequests, setFriendRequests] = useState([]);
  const [posts, setPosts] = useState([]);
  const [pageNumber, setPageNumber] = useState(0);
  const [pageSize, setPageSize] = useState(5);
  const [totalPages, setTotalPages] = useState(0);
  const navigate = useNavigate();

  const [editProfileForm, setEditProfileForm] = useState({
    name: '',
    nickname: '',
    profileImage: '',
  });

  const [newPostForm, setNewPostForm] = useState({
    privacy: '',
    postImage: '',
    description: '',
  });

  const [openModal, setOpenModal] = useState(false);
  const [openModalPost, setOpenModalPost] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      const result = await meInformations();
      const result2 = await listMyAndFriendsPosts(pageNumber, pageSize);

      setMeInfo(result);
      setFriendRequests(result.friendRequests);
      setPosts(result2.content);
      setTotalPages(result2.totalPages);
    };
    fetchData();
  }, []);

  useEffect(() => {
    const fetchData = async () => {
      const result = await meInformations();
      const result2 = await listMyAndFriendsPosts(pageNumber, pageSize);

      setMeInfo(result);
      setFriendRequests(result.friendRequests);
      setPosts(result2.content);
      setTotalPages(result2.totalPages);
    };
    fetchData();
  }, [editProfileForm, friendRequests, newPostForm, pageNumber, pageSize]);

  function handleNextPage() {
    if (pageNumber < totalPages - 1) {
      setPageNumber(pageNumber + 1);
    }
  }

  function handlePrevPage() {
    if (pageNumber > 0) {
      setPageNumber(pageNumber - 1);
    }
  }

  function handlePageSizeChange(event) {
    const newSize = parseInt(event.target.value, 10);
    setPageSize(newSize);
  }

  async function handleFriendsButton() {
    const searchResults = meInfo.friendships
    await searchFriends("")
    navigate('/friends', { state: { searchResults } });
  }

  const handleRegisterFormChange = (event) => {
    setEditProfileForm({
      ...editProfileForm,
      [event.target.name]: event.target.value,
    });
  };

  const handleNewPostFormChange = (event) => {
    setNewPostForm({
      ...newPostForm,
      [event.target.name]: event.target.value,
    });
  };

  const handleAcceptRequest = async (senderId) => {
    await acceptFriendRequest(senderId);
    setFriendRequests(
      friendRequests.filter((req) => req.senderId.id !== senderId)
    );
    toast(`Friendship request successfully accepted!`);
  };

  const handleRegisterSubmit = async (event) => {
    event.preventDefault();
    try {
      await editProfile(editProfileForm);
      toast(`Successful saved changes!`);
      setOpenModal(false);
      setEditProfileForm({
        name: '',
        nickname: '',
        profileImage: '',
      });
    } catch (error) {
      toast(`${error.response.statusText}!`);
    }
  };

  const handleNewPostSubmit = async (event) => {
    event.preventDefault();
    try {
      await createPost(newPostForm);
      toast(`Publication made successfully!`);
      setOpenModalPost(false);
      setNewPostForm({
        description: '',
        privacy: '',
        postImage: '',
      });
    } catch (error) {
      toast(`${error.response.statusText}!`);
    }
  };

  return (
    <>
      <Navbar onSearch={(text) => console.log(`Searching for ${text}`)} />
      <Container maxWidth="md" className={classes.container}>
        <Box className={classes.leftContainer}>
          <img
            src={meInfo.profileImage}
            alt="Profile"
            className={classes.profileImage}
          />
          <Typography variant="h5">{meInfo.name}</Typography>
          <Typography variant="subtitle1">{meInfo.nickname}</Typography>
          <Box className={classes.buttonContainer}>
            <Button
              variant="outlined"
              color="primary"
              onClick={() => setOpenModal(true)}
              id="button-register"
            >
              Edit Profile
            </Button>

            <Modal
              open={openModal}
              onClose={() => setOpenModal(false)}
              aria-labelledby="Register Modal"
              aria-describedby="Modal for user registration"
              className="registerModal"
            >
              <form className="form" onSubmit={handleRegisterSubmit}>
                <Typography variant="h4" align="center" gutterBottom>
                  Edit Profile
                </Typography>
                <TextField
                  label="Name"
                  variant="outlined"
                  name="name"
                  value={editProfileForm.name}
                  onChange={handleRegisterFormChange}
                  className="textField"
                />
                <TextField
                  label="Nickname"
                  variant="outlined"
                  name="nickname"
                  value={editProfileForm.nickname}
                  onChange={handleRegisterFormChange}
                  className="textField"
                />
                <TextField
                  label="Profile Image"
                  variant="outlined"
                  name="profileImage"
                  value={editProfileForm.profileImage}
                  onChange={handleRegisterFormChange}
                  className="textField"
                />
                <Button
                  variant="contained"
                  color="primary"
                  type="submit"
                  className="button"
                >
                  Save
                </Button>
              </form>
            </Modal>

            <Button
              variant="contained"
              color="primary"
              size="small"
              onClick={handleFriendsButton}
            >
              Friends
            </Button>

            <Button
              variant="outlined"
              color="primary"
              onClick={() => setOpenModalPost(true)}
              id="button-register"
            >
              New Post
            </Button>

            <Modal
              open={openModalPost}
              onClose={() => setOpenModalPost(false)}
              aria-labelledby="Register Modal"
              aria-describedby="Modal for user registration"
              className="registerModal"
            >
              <form className="form" onSubmit={handleNewPostSubmit}>
                <Typography variant="h4" align="center" gutterBottom>
                  Create a post
                </Typography>
                <TextField
                  label="Description"
                  variant="outlined"
                  name="description"
                  value={newPostForm.description}
                  onChange={handleNewPostFormChange}
                  className="textField"
                />
                <TextField
                  label="Post image"
                  variant="outlined"
                  name="postImage"
                  value={newPostForm.postImage}
                  onChange={handleNewPostFormChange}
                  className="textField"
                />
                <Select
                  label="Privacy"
                  name="privacy"
                  value={newPostForm.privacy}
                  onChange={handleNewPostFormChange}
                  variant="outlined"
                  className="selectField"
                >
                  <MenuItem value="PRIVATE">PRIVATE</MenuItem>
                  <MenuItem value="PUBLIC">PUBLIC</MenuItem>
                </Select>
                <Button
                  variant="contained"
                  color="primary"
                  type="submit"
                  className="button"
                >
                  Publish
                </Button>
              </form>
            </Modal>
          </Box>
        </Box>
        <Box className={classes.rightContainer}>
          {friendRequests.map((req) => (
            <Box key={req.senderId.id} className={classes.friendRequestItem}>
              <img
                src={req.senderId.profileImage}
                alt="Sender"
                className={classes.friendRequestSenderImage}
              />
              <Typography variant="subtitle1">{req.senderId.name}</Typography>
              <Button
                variant="contained"
                color="primary"
                size="small"
                className={classes.acceptButton}
                onClick={() => handleAcceptRequest(req.senderId.id)}
              >
                Accept
              </Button>
            </Box>
          ))}
        </Box>
      </Container>

      <Container>
        <div className="page-size-select">
          <label htmlFor="page-size-select">Posts by page:</label>
          <input
            type="number"
            id="page-size-input"
            value={pageSize}
            onChange={handlePageSizeChange}
            className="numero-itens" />
        </div>

        {posts.map((post) => (
          <PostContainer key={post.id} post={post} meId={meInfo.email} />
        ))}

        <div className="botao-anterior-proximo">
          <button disabled={pageNumber === 0} onClick={handlePrevPage}>
            Back
          </button>
          <button disabled={pageNumber >= totalPages - 1} onClick={handleNextPage}>
            Next
          </button>
        </div>
      </Container>
    </>
  );
}
