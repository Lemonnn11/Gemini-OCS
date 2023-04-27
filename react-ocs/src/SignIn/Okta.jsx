import { useEffect, useRef } from 'react';
import { oktaConfig } from '../lib/oktaConfig';
import OktaSignIn from '@okta/okta-signin-widget';
import '@okta/okta-signin-widget/dist/css/okta-sign-in.min.css';
import { Navbar } from '../layouts/NavbarAndFooter/Navbar1';

const OktaWidget = ({ isSuccess, isError }) => {
    const widgetRef = useRef();

    useEffect(() => {

        if(!widgetRef.current){
            return false;
        }

        const widget = new OktaSignIn(oktaConfig);

        widget.showSignInToGetTokens({
            el: widgetRef.current,
        }).then(isSuccess).catch(isError);
    
    }, [isSuccess, isError])

    return(
        <div>
            <div className='login' style={{paddingTop: '50px', paddingBottom: '249px'}}>
            <div className='container mb-5' style={{marginTop: '160px'}}>
                <div ref={widgetRef}></div>
            </div>
        </div>
        </div>
    );
}

export default OktaWidget;