import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import "./css/App.css";
import MainPage from "./page/MainPage";
import LoginPage from "./components/Auth/LoginPage";

window.Kakao.init("ec5392ab51ef7b87b646b920cfa55d66");
console.log(window.Kakao.isInitialized());

function App() {
  return (
    <Router>
      <Switch>
        <Route exact path="/" component={MainPage} />
        <Route exact path="/signin" component={LoginPage} />
      </Switch>
    </Router>
  );
}

export default App;
