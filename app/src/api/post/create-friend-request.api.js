import { axiosInstance } from '../_base/axios-instance';

export async function createFriendRequest( userId ) {
  const response = await axiosInstance.post(`/friends/send/${userId}`, {});

  return response.data;
}
