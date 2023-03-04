import { NavbarFriends, UserContainer } from '../../components';
import { useLocation } from 'react-router-dom';

import './search-friends.style.css';

export function SearchFriendsScreen() {
  const location = useLocation();
  const { searchResults } = location.state || {};

  return (
    <>
      <NavbarFriends onSearch={(text) => console.log(`Searching for ${text}`)} />

      <div className="search-user-div"></div>

      {searchResults.map((user) => (
        <UserContainer key={user.id} user={user} />
      ))}
    </>
  );
}
