import './index.css';
import { RouterProvider } from 'react-router-dom';
import { router } from './router';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function App() {
  return (
    <div className="App">
      <ToastContainer toastStyle={{ backgroundColor: 'white' }} />
      <RouterProvider router={router} />
    </div>
  );
}

export default App;
