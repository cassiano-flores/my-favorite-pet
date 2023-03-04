import { useState } from 'react';
import {
  Container,
  Grid,
  Typography,
  TextField,
  Button,
  Modal,
} from '@material-ui/core';
import { useNavigate } from 'react-router-dom';
import logo from '../../../assets/img/logo.png';
import { loginUser, registerUser } from '../../../api';
import { toast } from 'react-toastify';

import './home.style.css';

export function HomeScreen() {
  const navigate = useNavigate();
  const [form, setForm] = useState({ username: '', password: '' });
  const [registerForm, setRegisterForm] = useState({
    name: '',
    email: '',
    nickname: '',
    birthDate: '',
    password: '',
    profileImage: '',
  });
  const [openModal, setOpenModal] = useState(false);

  const handleFormChange = (event) => {
    setForm({ ...form, [event.target.name]: event.target.value });
  };

  const handleRegisterFormChange = (event) => {
    setRegisterForm({
      ...registerForm,
      [event.target.name]: event.target.value,
    });
  };

  const handleLoginSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await loginUser(form);
      toast(`Welcome, ${response.name}!`);
      navigate('/profile');
    } catch (error) {
      toast(`${error.response.statusText}!`);
    }
  };

  const handleRegisterSubmit = async (event) => {
    event.preventDefault();
    try {
      await registerUser(registerForm);
      toast(`Successful registered user!`);
      setOpenModal(false);
    } catch (error) {
      toast(`${error.response.statusText}!`);
    }
  };

  return (
    <div className="container-home-screen">
      <Container>
        <Grid container>
          <Grid item xs={12} md={6} className="leftSide">
            <Typography variant="h2" align="center" gutterBottom>
              Welcome!
            </Typography>
            <img src={logo} alt="Logo" className="logo" />
            <Typography variant="h4" align="center" gutterBottom>
              My Favorite Pet
            </Typography>
          </Grid>
          <Grid item xs={12} md={6} className="rightSide">
            <form className="form" onSubmit={handleLoginSubmit}>
              <TextField
                label="Username"
                variant="outlined"
                name="username"
                value={form.username}
                onChange={handleFormChange}
                className="textField"
              />
              <TextField
                label="Password"
                variant="outlined"
                name="password"
                value={form.password}
                onChange={handleFormChange}
                type="password"
                className="textField"
              />
              <Button
                variant="contained"
                color="primary"
                type="submit"
                className="button"
              >
                Login
              </Button>
            </form>
            <Button
              variant="outlined"
              color="primary"
              onClick={() => setOpenModal(true)}
              id="button-register"
            >
              Register
            </Button>
            <Modal
              open={openModal}
              onClose={() => setOpenModal(false)}
              aria-labelledby="Register Modal"
              aria-describedby="Modal for user registration"
              className="registerModal"
            >
              <form className="form" onSubmit={handleRegisterSubmit}>
                <Typography variant="h4" align="center" gutterBottom>
                  Register
                </Typography>
                <TextField
                  label="Name"
                  variant="outlined"
                  name="name"
                  value={registerForm.name}
                  onChange={handleRegisterFormChange}
                  className="textField"
                />
                <TextField
                  label="Email"
                  variant="outlined"
                  name="email"
                  value={registerForm.email}
                  onChange={handleRegisterFormChange}
                  className="textField"
                />
                <TextField
                  label="Nickname"
                  variant="outlined"
                  name="nickname"
                  value={registerForm.nickname}
                  onChange={handleRegisterFormChange}
                  className="textField"
                />
                <TextField
                  variant="outlined"
                  name="birthDate"
                  type="date"
                  value={registerForm.birthDate}
                  onChange={handleRegisterFormChange}
                  className="textField"
                />
                <TextField
                  label="Password"
                  variant="outlined"
                  name="password"
                  type="password"
                  value={registerForm.password}
                  onChange={handleRegisterFormChange}
                  className="textField"
                />
                <TextField
                  label="Profile Image"
                  variant="outlined"
                  name="profileImage"
                  value={registerForm.profileImage}
                  onChange={handleRegisterFormChange}
                  className="textField"
                />
                <Button
                  variant="contained"
                  color="primary"
                  type="submit"
                  className="button"
                >
                  Register
                </Button>
              </form>
            </Modal>
          </Grid>
        </Grid>
      </Container>
    </div>
  );
}
