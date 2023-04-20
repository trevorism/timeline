describe('home page', () => {
    it('passes', () => {
        cy.visit('https://timeline.draw.trevorism.com')
        cy.title().should('equal', 'Trevorism Timeline')
    })
})