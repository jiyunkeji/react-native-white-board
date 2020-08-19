import { requireNativeComponent, ViewProps } from 'react-native';
import React, { Component } from 'react';
import type { JYWhiteBoardRGB } from './Types';

export interface JYWhiteBoardViewProps extends ViewProps {
  strokeColorRGB?: JYWhiteBoardRGB;
}
const WhiteBoardView = requireNativeComponent('JYWhiteBoardView');

export class JYWhiteBoardView extends Component<JYWhiteBoardViewProps, {}> {
  render() {
    return <WhiteBoardView {...this.props} />;
  }
}
