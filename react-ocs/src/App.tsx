import React from 'react';
import './App.css';
import { Navbar } from './layouts/NavbarAndFooter/Navbar';
import { SciencePlans } from './layouts/SciencePlansPage/SciencePlansPage';
import { HomePage } from './layouts/HomePage/HomePage';
import { Route, Switch } from 'react-router-dom';
import { SciencePlanInfoPage } from './layouts/SciencePlanInfoPage/SciencePlanInfoPage';

function App() {
  return (
    <div>
      <Navbar />
      <Switch>
        <Route path={"/"} exact>
          <HomePage />
        </Route>

        <Route path={"/sciencePlans"}>
          <SciencePlans />
        </Route>

        <Route path={"/sciencePlan/:planNo"}>
          <SciencePlanInfoPage />
        </Route>
      </Switch>

    </div>
  );
}

export default App;
