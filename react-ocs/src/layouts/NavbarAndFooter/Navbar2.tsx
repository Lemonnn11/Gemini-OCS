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
      <div className='container-fluid'>
        {/* <a className='navbar-brand' >Gemini System</a> */}
        <img src={require('./../../images/Group_17.png')} alt=""/>
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
      <ul className="navbar-nav me-auto mb-2 mb-lg-0">
        <li className="nav-item">
          <NavLink className="nav-link" to="/"></NavLink>
        </li>
      </ul>
      {authState.isAuthenticated ? <button style={{marginRight: '20px'}} className="btn btn-outline-success btn-outline-light" type="submit" onClick={handleLogout}>Sign out</button>:  
       <Link style={{marginRight: '20px'}} type="button" className="btn btn-outline-success btn-outline-light" to={"/login"}>Sign in</Link>}
    </div>
      </div>
    </nav>
    );
}