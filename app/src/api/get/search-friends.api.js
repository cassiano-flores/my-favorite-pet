import { axiosInstance } from "../_base/axios-instance";

export async function searchFriends({ text }) {
  const response = await axiosInstance.get(`/friends?text=${text}`, {});

  return response.data;
}
