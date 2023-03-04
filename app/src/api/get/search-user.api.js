import { axiosInstance } from "../_base/axios-instance";

export async function searchUser({ text }) {
  const response = await axiosInstance.get(`/users?text=${text}`, {});

  return response.data;
}
