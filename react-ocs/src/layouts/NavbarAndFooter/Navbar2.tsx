import { Link, NavLink } from "react-router-dom";
import { useOktaAuth} from '@okta/okta-react';
import { SpinnerLoading } from "../Utils/SpinnerLoading";

export const Navbarr = () => {

  const {oktaAuth, authState } = useOktaAuth();

  console.log(authState);

  if(!authState){
    return <SpinnerLoading/>
  }

  const handleLogout = async() => oktaAuth.signOut();

    return (
        <nav className='navbar navbar-expand-lg navbar-dark bg-dark shadow'>
      <div className='container-fluid' style={{margin:'10px'}}>
        <a className='navbar-brand' >Gemini System</a>
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
      <ul className="navbar-nav me-auto mb-2 mb-lg-0">
        <li className="nav-item">
          <NavLink className="nav-link" to="/"></NavLink>
        </li>
      </ul>
      {authState.isAuthenticated ? <button className="btn btn-outline-success btn-outline-light" type="submit" onClick={handleLogout}>Sign out</button>:  
       <Link type="button" className="btn btn-outline-success btn-outline-light" to={"/login"}>Sign in</Link>}
    </div>
      </div>
    </nav>
    );
}