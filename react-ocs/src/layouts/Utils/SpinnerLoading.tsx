export const SpinnerLoading = () => {
    return (
        <div className="d-flex justify-content-center mt-5" style={{height: 550}}>
            <div className="spinner-border text-secondary" role= 'status'>
                <span className="visually-hidden">
                    Loading...
                </span>
            </div>
        </div>
    );
}