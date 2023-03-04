import { axiosInstance } from '../_base/axios-instance';

export async function acceptFriendRequest( userId ) {
  const response = await axiosInstance.put(`/friends/accept/${userId}`, {});

  return response.data;
}
