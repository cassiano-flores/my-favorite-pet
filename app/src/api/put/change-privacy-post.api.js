import { axiosInstance } from '../_base/axios-instance';

export async function changePrivacyPost( postId ) {
  const response = await axiosInstance.put(`/posts/privacy/${postId}`, {});

  return response.data;
}
