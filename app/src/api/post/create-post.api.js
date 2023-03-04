import { axiosInstance } from '../_base/axios-instance';

export async function createPost( form ) {
  const response = await axiosInstance.post(`/posts`, {
    privacy: form.privacy,
    postImage: form.postImage,
    description: form.description
  });

  return response.data;
}
