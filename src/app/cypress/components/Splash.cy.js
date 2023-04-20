import Splash from '../../src/components/Splash.vue'

describe('<Splash />', () => {
    it('renders title', () => {
        cy.mount(Splash)
        cy.get('h2').should('contains.text', 'Trevorism Timeline')
    })

    it('contains two buttons', () => {
        cy.mount(Splash)
        cy.get('h2').should('contains.text', 'Trevorism Timeline')
    })
})
