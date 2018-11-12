import React from 'react';
import { Route, Switch } from 'react-router-dom';

import HomeContainer from './containers/home/home.container';

const routes = [
  {
    path: '/',
    exact: true,
    component: HomeContainer,
  }
];

export default function ItemsSwitch() {
  return (
    <Switch >
      {routes.map((route, i) => (
        <Route key={`route-${i}`} path={route.path} exact={route.exact} component={route.component} />
      ))}
      {/*<Route component={NotFoundContainer} />*/}
    </Switch>
  );
}
