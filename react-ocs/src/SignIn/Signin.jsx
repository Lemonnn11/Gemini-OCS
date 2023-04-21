import { Redirect } from 'react-router-dom';
import { useOktaAuth } from '@okta/okta-react';
import { SpinnerLoading } from '../layouts/Utils/SpinnerLoading';
import OktaWidget from './Okta';

const SignInWidget = ({config}) => {
    const{ oktaAuth, authState } = useOktaAuth();
    
    const isSuccess = (tokens) => {
        oktaAuth.handleLoginRedirect(tokens);
    };

    const isError = (err) => {
        console.log('Error: ', err)
    }

    if(!authState){
        return (<SpinnerLoading/>);
    }

    if(authState.isAuthenticated){
        return <Redirect to={{ pathname: '/'}}/>
    }
    else{
        return <OktaWidget config={config} isSuccess={isSuccess} isError={isError}/>
    }
}

export default SignInWidget;