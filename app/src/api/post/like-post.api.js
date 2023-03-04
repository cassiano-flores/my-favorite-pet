import { axiosInstance } from '../_base/axios-instance';

export async function likePost( postId ) {
  const response = await axiosInstance.post(`/posts/like/${postId}`, {});

  return response.data;
}
