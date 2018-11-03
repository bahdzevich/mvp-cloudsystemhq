import React from 'react';
import { Route, Switch } from 'react-router-dom';

import HomeContainer from './containers/home/home.container';
import InvoiceContainer from './containers/invoice/invoice.container';
import CreateContainer from './containers/create/create.container';
import NotFoundContainer from './containers/not-found/not-found.container';

const routes = [
  {
    path: '/',
    exact: true,
    component: HomeContainer,
  },
  {
    path: '/invoice',
    component: InvoiceContainer,
  },
  {
    path: '/create',
    component: CreateContainer,
  }
];

export default function ItemsSwitch() {
  return (
    <Switch >
      {routes.map((route, i) => (
        <Route key={`route-${i}`} path={route.path} exact={route.exact} component={route.component} />
      ))}
      <Route component={NotFoundContainer} />
    </Switch>
  );
}
