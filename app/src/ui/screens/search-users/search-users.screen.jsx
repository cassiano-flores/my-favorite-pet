import './search-users.style.css';
import { Navbar, UserContainer } from '../../components';
import { useLocation } from 'react-router-dom';

export function SearchUsersScreen() {
  const location = useLocation();
  const { searchResults } = location.state || {};

  return (
    <>
      <Navbar onSearch={(text) => console.log(`Searching for ${text}`)} />

      <div className="search-user-div"></div>

      {searchResults.map((user) => (
        <UserContainer key={user.id} user={user} />
      ))}
    </>
  );
}
