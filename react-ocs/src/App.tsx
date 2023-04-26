import React from 'react';
import './App.css';
import { Navbar } from './layouts/NavbarAndFooter/Navbar1';
import { SciencePlans } from './layouts/SciencePlansPage/SciencePlansPage';
import { Homepage } from './layouts/Homepage/Homepage';
import { Route, Switch, useHistory } from 'react-router-dom';
import { SciencePlanInfoPage } from './layouts/SciencePlanInfoPage/SciencePlanInfoPage';
import { oktaConfig } from './lib/oktaConfig';
import { OktaAuth, toRelativeUrl } from '@okta/okta-auth-js';
import { Security, LoginCallback } from '@okta/okta-react';
import SignInWidget from "./SignIn/Signin";
import { CreateSciencePlanPage } from './CreateSciencePlanPage/CreateSciencePlan';
import { ActionTab } from './layouts/SciencePlanAction/ActionTab';
import { EditSciencePlanPage } from './layouts/EditSciencePlan/EditSciencePlan';

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

        <Route path={"/createSciencePlan"}>
          <CreateSciencePlanPage />
        </Route>

        <Route path={"/editSciencePlan/:planNo"}>
          <EditSciencePlanPage />
        </Route>

        <Route path={"/manageSciencePlan/:planNo"}>
          <ActionTab />
        </Route>

        <Route path={"/login"} render={() => <SignInWidget config={LoginCallback}/>}/>
      </Switch>
      </Security>
    </div>
  );
}

export default App;
