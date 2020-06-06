const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {recipes: []};
	}

	componentDidMount() {
		client({method: 'GET', path: '/api/recipes'}).done(response => {
			this.setState({recipes: response.entity._embedded.recipes});
		});
	}

	render() {
		return (
			<RecipeList recipes={this.state.recipes}/>
		)
	}
}