import {BrowserRouter as Router, Route} from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import MainPage from "./pages/MainPage";
import RegisterPage from "./pages/RegisterPage";
import AuthContextProvider from "./context/AuthContext";
import AuthHoc from "./utils/hoc/AuthHoc";
import "./asset/css/fontawesome/fontawesome-all.css";
import "./asset/css/fontawesome/style.css";
import MyReviewPage from "./pages/MyReviewPage";

function App() {
  return (
<<<<<<< HEAD
      <Router>
        <Route exact path="/" component={MainPage}/>
        <Route exact path="/login" component={LoginPage}/>
        <Route exact path="/register" component={RegisterPage}/>
      </Router>
=======
    <Router>
      <AuthContextProvider>
        <Route exact path="/" component={MainPage} />
        <Route exact path="/login" component={AuthHoc(LoginPage, false)} />
        <Route
          exact
          path="/register"
          component={AuthHoc(RegisterPage, false)}
        />
        <Route exact path="/products/categories/:id" component={MainPage} />
        <Route
          exact
          path="/myPage/review"
          component={AuthHoc(MyReviewPage, true)}
        />
      </AuthContextProvider>
    </Router>
>>>>>>> 2e8e26f9b3f739b8c1815878baa3ee16a931aecf
  );
}

export default App;
