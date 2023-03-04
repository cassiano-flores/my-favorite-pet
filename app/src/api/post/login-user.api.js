import { axiosInstance } from '../_base/axios-instance';

export async function loginUser( form ) {
  const response = await axiosInstance.post(
    `/users/login`,
    {},
    {
      auth: {
        username: form.username,
        password: form.password,
      },
    }
  );

  return response.data;
}
