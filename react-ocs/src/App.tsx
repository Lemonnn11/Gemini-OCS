import React from 'react';
import './App.css';
import { Navbar } from './layouts/NavbarAndFooter/Navbar';
import { SciencePlans } from './layouts/SciencePlansPage/SciencePlansPage';
import { Homepage } from './layouts/Homepage/Homepage';
import { Route, Switch, useHistory } from 'react-router-dom';
import { SciencePlanInfoPage } from './layouts/SciencePlanInfoPage/SciencePlanInfoPage';
import { oktaConfig } from './lib/oktaConfig';
import { OktaAuth, toRelativeUrl } from '@okta/okta-auth-js';
import { Security, LoginCallback } from '@okta/okta-react';
import SignInWidget from "./SignIn/Signin"

const oktaAuth = new OktaAuth(oktaConfig);

function App() {

  const history = useHistory();

  const customSignInHandler = () => {
    history.push('/login');
  };

  const restoreToOriginalUri = async (_oktaAuth: any, originalUri: any) => {
    history.replace(toRelativeUrl(originalUri || '/', window.location.origin))
  };
  
  return (
    <div>
      <Security oktaAuth={oktaAuth} restoreOriginalUri={restoreToOriginalUri} onAuthRequired={customSignInHandler}>
      <Navbar />
      <Switch>
        <Route path={"/"} exact>
          <Homepage />
        </Route>

        <Route path={"/sciencePlans"}>
          <SciencePlans />
        </Route>

        <Route path={"/sciencePlan/:planNo"}>
          <SciencePlanInfoPage />
        </Route>

        <Route path={"/login"} render={() => <SignInWidget config={LoginCallback}/>}/>
      </Switch>
      </Security>
    </div>
  );
}

export default App;
