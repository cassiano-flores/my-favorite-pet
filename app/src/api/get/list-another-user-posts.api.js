import { axiosInstance } from "../_base/axios-instance";

export async function listAnotherUserPosts( userId ) {
  const response = await axiosInstance.get(`/posts/${userId}`, {});

  return response.data;
}
