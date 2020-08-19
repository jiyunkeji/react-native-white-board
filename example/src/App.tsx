/**
 * Sample React Native App
 *
 * adapted from App.js generated by the following command:
 *
 * react-native init example
 *
 * https://github.com/facebook/react-native
 */

import React, { Component } from 'react';
import { StyleSheet, Text, View, TouchableOpacity } from 'react-native';
import JYWhiteBoardView, {
  JYWhiteBoardManager,
  JYWhiteBoardInitOptions,
  JYWhiteBoardRGB,
  JYWhiteBoardJoinRoomOptions,
} from 'react-native-white-board';
interface State {
  strokeColorRGB?: JYWhiteBoardRGB;
}
export default class App extends Component<any, State> {
  state: State = {
    strokeColorRGB: new JYWhiteBoardRGB(255, 0, 0),
  };
  manager?: JYWhiteBoardManager;
  constructor(props: any) {
    super(props);
  }
  componentDidMount() {
    setTimeout(() => {
      this.init();
    }, 3000);
    // this.init();
  }
  componentWillUnmount() {
    this.manager?.destroy();
  }
  async init() {
    let manager = await JYWhiteBoardManager.init(
      new JYWhiteBoardInitOptions('ehuvwNLlEeqTIve5DWs2Gg/KheA3hZvWA8XEA')
    ).catch((ex: any) => {
      console.log(`init: ${JSON.stringify(ex)}`);
    });
    this.manager = manager as JYWhiteBoardManager;
    this.manager.addListener('onRoomStateChanged', (data: any) => {
      console.log(`callback:   ${JSON.stringify(data)}`);
    });
    console.log(`joinRoom:`);
    await this.manager
      .joinRoom(
        new JYWhiteBoardJoinRoomOptions(
          'f31ed380d6e511ea9be4d9045066d16e',
          'WHITEcGFydG5lcl9pZD00NXRONWl5TWRWaDhYTjN1JnNpZz1jNGEwODEyOGE3MTYzOTQ2MGFkMWEwMmRkODY3ZTE5M2NhZjY5MGUwOmFrPTQ1dE41aXlNZFZoOFhOM3UmY3JlYXRlX3RpbWU9MTU5NjYwOTM3MDUzMSZleHBpcmVfdGltZT0xNjI4MTQ1MzcwNTMxJm5vbmNlPTE1OTY2MDkzNzA1MzEwMCZyb2xlPXJvb20mcm9vbUlkPWYzMWVkMzgwZDZlNTExZWE5YmU0ZDkwNDUwNjZkMTZlJnRlYW1JZD1laHV2d05MbEVlcVRJdmU1RFdzMkdn'
        )
      )
      .catch((ex: any) => {
        console.log(`joinRoom: ${JSON.stringify(ex)}`);
      });
  }
  render() {
    return (
      <View style={styles.container}>
        <TouchableOpacity
          onPress={() => {
            this.setState({
              strokeColorRGB: new JYWhiteBoardRGB(255, 255, 0),
            });
          }}
        >
          <Text style={styles.welcome}>☆JYWhiteBoard example☆</Text>
        </TouchableOpacity>

        <View style={styles.white_b_c}>
          <JYWhiteBoardView
            style={styles.white_b}
            strokeColorRGB={this.state.strokeColorRGB}
          />
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    width: '100%',
    height: '100%',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  white_b_c: {
    flex: 1,
    width: '100%',
    backgroundColor: '#0f0',
  },
  white_b: {
    width: '100%',
    height: '100%',
    backgroundColor: '#ff0',
  },
});
