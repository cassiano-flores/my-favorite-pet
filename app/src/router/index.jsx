import { createBrowserRouter } from 'react-router-dom';
import { HomeScreen, ProfileScreen, SearchUsersScreen, ProfileUserScreen, SearchFriendsScreen } from '../ui/screens';

export const router = createBrowserRouter([
  {
    path: '/',
    element: <HomeScreen />,
  },
  {
    path: '/profile',
    element: <ProfileScreen />,
  },
  {
    path: '/search/users',
    element: <SearchUsersScreen />,
  },
  {
    path: '/user/:userId',
    element: <ProfileUserScreen />,
  },
  {
    path: '/friends',
    element: <SearchFriendsScreen />,
  },
]);
