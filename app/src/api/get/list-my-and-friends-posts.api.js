import { axiosInstance } from "../_base/axios-instance";

export async function listMyAndFriendsPosts( pageNumber, pageSize ) {
  const response = await axiosInstance.get(`/posts?size=${pageSize}&page=${pageNumber}`, {});

  return response.data;
}
