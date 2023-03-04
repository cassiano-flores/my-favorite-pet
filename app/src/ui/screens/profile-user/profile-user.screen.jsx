import React, { useEffect, useState } from 'react';
import {
  Container,
  Box,
  Typography,
  Button
} from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import {
  meInformations,
  removeFriend,
  createFriendRequest,
  listAnotherUserPosts,
  userProfile,
} from '../../../api';
import { toast } from 'react-toastify';
import { Navbar, PostContainerUser } from '../../components';
import { useParams } from 'react-router-dom';

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
    overflowY: 'invisible',
    height: '400px',
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

export function ProfileUserScreen() {
  const classes = useStyles();
  const [meInfo, setMeInfo] = useState({});
  const [userInfo, setUserInfo] = useState({});
  const [posts, setPosts] = useState([]);
  const [isFriend, setIsFriend] = useState(false);
  const { userId } = useParams();

  useEffect(() => {
    const fetchData = async () => {
      const result = await meInformations();
      const result2 = await listAnotherUserPosts(userId);
      const result3 = await userProfile(userId);

      setMeInfo(result);
      setPosts(result2.content);
      setUserInfo(result3);
    };
    fetchData();
  }, []);

  useEffect(() => {
    const fetchData = async () => {
      const result = await meInformations();
      const result2 = await listAnotherUserPosts(userId);
      const result3 = await userProfile(userId);

      setMeInfo(result);
      setPosts(result2.content);
      setUserInfo(result3);
    };
    fetchData();

    if (meInfo.friendships && meInfo.friendships.some((friend) => friend.id == userId)) {
      setIsFriend(true);
    } else {
      setIsFriend(false)
    }
  }, [meInfo, meInfo.friendships, userId]);

  async function handleRemoveFriend() {
    try {
      await removeFriend(userId);
      toast(`User removed successfully`);
    } catch (error) {
      toast(`${error.response.statusText}!`);
    }
  }

  async function handleAddFriend() {
    try {
      await createFriendRequest(userId);
      toast(`Request sent successfully`);
    } catch (error) {
      toast(`${error.response.statusText}!`);
    }
  }

  return (
    <>
      <Navbar onSearch={(text) => console.log(`Searching for ${text}`)} />

      <Container maxWidth="md" className={classes.container}>
        <Box className={classes.leftContainer}>
          <img
            src={userInfo.profileImage}
            alt="Profile"
            className={classes.profileImage}
          />
          <Typography variant="h5">{userInfo.name}</Typography>
          <Typography variant="subtitle1">{userInfo.nickname}</Typography>
        </Box>

        <Box className={classes.rightContainer}>
          {isFriend === true ? (
            <Button
              variant="contained"
              color="primary"
              size="small"
              className={classes.acceptButton}
              onClick={() => handleRemoveFriend()}
            >
              Remove friend
            </Button>
          ) : (
            <Button
              variant="contained"
              color="primary"
              size="small"
              className={classes.acceptButton}
              onClick={() => handleAddFriend()}
            >
              Send friend request
            </Button>
          )}
        </Box>
      </Container>

      <Container>
        {posts.map((post) => (
          <PostContainerUser key={post.id} post={post} />
        ))}
      </Container>
    </>
  );
}
