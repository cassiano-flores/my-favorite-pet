import { Avatar, Box, Typography } from '@material-ui/core';
import { useNavigate } from 'react-router-dom';

import './users-container.style.css';

export function UserContainer({ user }) {
  const navigate = useNavigate();

  return (
    <Box
      display="flex"
      alignItems="center"
      justifyContent="center"
      padding={2}
      boxShadow={1}
      onClick={() => navigate(`/user/${user.id}`)}
      className="box-user"
    >
      <Box marginRight={2} className="post-container-left">
        <Avatar src={user.profileImage} alt={user.name} />
        <Typography variant="subtitle2">{user.name}</Typography>
      </Box>

      <Box flexGrow={1} className="post-container-middle">
        <Typography variant="body1">{user.email}</Typography>
      </Box>

      <Box marginLeft={2} className="post-container-right">
        <Typography variant="caption">{user.birthdate}</Typography>
      </Box>
    </Box>
  );
}
