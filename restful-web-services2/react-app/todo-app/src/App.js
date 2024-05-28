import './App.css';
import TodoApp from './components/todo/TodoApp'
//import {FifthComponent} from './components/learning-examples/FirstComponent' // export default만 {}를 사용하지 않음.

function App() {
  return (
    <div className="App">  
      <TodoApp />
    </div>
  );
}

// property를 전달하는 방법, 함수 또는 변수를 전달할 수 있음.
/*{ <PlayingWithProps property1="value1" property2="value2" /> 

function PlayingWithProps(properties){ //or {property1, property2}
  console.log(properties)
  return (
    <div>
      Props
    </div>
  )
} }*/

export default App;
