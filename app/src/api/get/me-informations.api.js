import { axiosInstance } from "../_base/axios-instance";

export async function meInformations() {
  const response = await axiosInstance.get(`/users/me`, {});

  return response.data;
}
