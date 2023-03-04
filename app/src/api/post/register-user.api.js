import { axiosInstance } from '../_base/axios-instance';

export async function registerUser( form ) {
  const response = await axiosInstance.post(`/users/register`, {
    name: form.name,
    email: form.email,
    nickname: form.nickname,
    birthDate: form.birthDate,
    password: form.password,
    profileImage: form.profileImage
  });

  return response.data;
}
