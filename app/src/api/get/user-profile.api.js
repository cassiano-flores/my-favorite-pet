import { axiosInstance } from "../_base/axios-instance";

export async function userProfile( userId ) {
  const response = await axiosInstance.get(`/users/${userId}`, {});

  return response.data;
}
