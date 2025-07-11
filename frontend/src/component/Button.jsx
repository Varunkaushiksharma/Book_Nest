import './Button.css'
function Button(props){
    return(
        <div>
            <button className="butn">{props.name}</button>
        </div>
    )
}

export default Button;