import { axiosInstance } from '../_base/axios-instance';

export async function editProfile( form ) {
  const response = await axiosInstance.put(`/users/me/profile`, {
    name: form.name,
    nickname: form.nickname,
    profileImage: form.profileImage
  });

  return response.data;
}
