import { Navbar } from "../NavbarAndFooter/Navbar1";
import { Navbarr } from "../NavbarAndFooter/Navbar2";
import { useOktaAuth } from '@okta/okta-react';


export const Homepage = () => {

    const{ oktaAuth, authState } = useOktaAuth();

    return(
        <div>
            {authState?.isAuthenticated ? <Navbar/>: <Navbarr/>}
            <div className="d-flex justify-content-center mt-5">Welcome to OCS</div>
        </div>
    );
}