import { axiosInstance } from '../_base/axios-instance';

export async function commentPost( postId, { comment }) {
  const response = await axiosInstance.post(`/posts/comment/${postId}`, {
    comment
  });

  return response.data;
}
