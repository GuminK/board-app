import React from 'react';



const Button = ({ label, onClick, disabled = false, variant = 'primary' }) => {
    return (
        <button
            onClick={onClick}
            disabled={disabled}
            className={`btn btn-${variant}`}
        >
            {label}
        </button>
    );
};

export default Button;