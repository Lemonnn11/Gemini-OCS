import { Navbar } from "../NavbarAndFooter/Navbar1";
import { Navbarr } from "../NavbarAndFooter/Navbar2";
import { useOktaAuth } from '@okta/okta-react';


export const Homepage = () => {

    const{ oktaAuth, authState } = useOktaAuth();

    return(
        <div className="home">
            {authState?.isAuthenticated ? <Navbar/>: <Navbarr/>}
            <div className="d-flex justify-content-center mt-5 text-light" style={{height: '810px'}}>
            <article style={{marginTop: '100px', marginLeft: '810px'}}>
                        <h1 style={{marginLeft: '-2px'}}>Gemini Observatory</h1>
                        <h3>Exploring the universe, Sharing its Wonders</h3><br/>
                        <a>The International Gemini Observatory consists of twin 8.1- meter diameter optical/infrared</a>
                        <p> telescopes located on two of the best-observing sites on the planet.</p>
                        <button className="btn btn-light mt-3">Expolore</button>
                    </article>
            </div>
        </div>
    );
}