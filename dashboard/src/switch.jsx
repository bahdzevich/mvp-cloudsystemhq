import React from 'react';
import { Route, Switch } from 'react-router-dom';

import Home from './components/home';
import Invoice from './components/invoice';
import Create from './components/create';
import NotFound from './components/not-found';

const routes = [
  {
    path: '/',
    exact: true,
    component: Home,
  },
  {
    path: '/invoice',
    component: Invoice,
  },
  {
    path: '/create',
    component: Create,
  }
];

export default function ItemsSwitch() {
  return (
    <Switch >
      {routes.map((route, i) => (
        <Route key={`route-${i}`} path={route.path} exact={route.exact} component={route.component} />
      ))}
      <Route component={NotFound} />
    </Switch>
  );
}
