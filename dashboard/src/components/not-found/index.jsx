import React, { Component } from 'react'
import { Link } from 'react-router-dom';
import Eror from './icons/404.svg';
import './not-found.scss';

export default class NotFound extends Component {
  render() {
    return (
      <div className='not-found'>
        <Eror width={54} height={54}/>
        <p className='not-found__text text'>Page not found.</p>
        <p className='not-found__text text'>Go back to the <Link to='/'>main page</Link>?</p>
      </div>
    )
  };
}
