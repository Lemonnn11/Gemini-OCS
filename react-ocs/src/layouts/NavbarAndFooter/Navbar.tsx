import { NavLink } from "react-router-dom";

export const Navbar = () => {
    return (
        <nav className='navbar navbar-expand-lg navbar-dark bg-dark shadow'>
      <div className='container-fluid' style={{margin:'10px'}}>
        <a className='navbar-brand' >Gemini System</a>
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
      <ul className="navbar-nav me-auto mb-2 mb-lg-0">
        <li className="nav-item">
          <NavLink className="nav-link" to="/">Home</NavLink>
        </li>
        <li className="nav-item">
          <NavLink className="nav-link" to="/sciencePlans">SciencePlan</NavLink>
        </li>
      </ul>
      <button className="btn btn-outline-success btn-outline-light" type="submit">Sign in</button>
    </div>
      </div>
    </nav>
    );
}