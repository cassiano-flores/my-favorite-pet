import { axiosInstance } from '../_base/axios-instance';

export async function removeFriend( userId ) {
  return await axiosInstance.delete(`/friends/remove/${userId}`);
}
