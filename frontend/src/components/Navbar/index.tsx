import { ReactComponent as GithubIcon } from 'assets/img/github.svg'

function Navbar() {
    return (
        <header>
      <nav>
        <div>
          <h1>DS Movie</h1>
          <a href="https://github.com/gregorydossantos" target="_blank" rel="noreferrer">
                <div className="dsmovie-contact-container">
                    <GithubIcon />
                    <p className="dsmovie-contact-link">/gregorycastezana</p>
                </div>
            </a>
        </div>
      </nav>
    </header>
    );
}

export default Navbar;